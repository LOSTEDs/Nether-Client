/*
Decompiled By LOSTED
https://github.com/LOSTEDs
LOSTED#8754
https://www.youtube.com/watch?v=xg2M21todDU&t=55s
"...Minecraft client created by professional developers exclusively for me..." - SuchSpeed
Here is a better way to say this, "...Minecraft client skidded by some random script kittens exclusively for me"
Please SuchSpeed, don't sue me... I just dumped the source...
For Educational Purposes Only...
*/

package net.optifine.entity.model.anim;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.src.Config;

public class ExpressionParser {
    private IExpressionResolver expressionResolver;
    
    public ExpressionParser(IExpressionResolver expressionResolver) {
        this.expressionResolver = expressionResolver;
    }
    
    public IExpressionFloat parseFloat(String str) throws ParseException {
        IExpression iexpression = parse(str);
        if (!(iexpression instanceof IExpressionFloat))
            throw new ParseException("Not a float expression: " + iexpression.getExpressionType()); 
        return (IExpressionFloat)iexpression;
    }
    
    public IExpressionBool parseBool(String str) throws ParseException {
        IExpression iexpression = parse(str);
        if (!(iexpression instanceof IExpressionBool))
            throw new ParseException("Not a boolean expression: " + iexpression.getExpressionType()); 
        return (IExpressionBool)iexpression;
    }
    
    public IExpression parse(String str) throws ParseException {
        try {
            Token[] tokens = TokenParser.parse(str);
            if (tokens == null)
                return null; 
            Deque<Token> deque = new ArrayDeque<>(Arrays.asList(tokens));
            return parseInfix(deque);
        } catch (IOException e) {
            throw new ParseException(e.getMessage(), e);
        } 
    }
    
    private IExpression parseInfix(Deque<Token> deque) throws ParseException {
        if (deque.isEmpty())
            return null; 
        List<IExpression> listExpr = new LinkedList<>();
        List<Token> listOperTokens = new LinkedList<>();
        IExpression expr = parseExpression(deque);
        checkNull(expr, "Missing expression");
        listExpr.add(expr);
        while (true) {
            Token tokenOper = deque.poll();
            if (tokenOper == null)
                return makeInfix(listExpr, listOperTokens); 
            if (tokenOper.getType() != TokenType.OPERATOR)
                throw new ParseException("Invalid operator: " + tokenOper); 
            IExpression expr2 = parseExpression(deque);
            checkNull(expr2, "Missing expression");
            listOperTokens.add(tokenOper);
            listExpr.add(expr2);
        } 
    }
    
    private IExpression makeInfix(List<IExpression> listExpr, List<Token> listOper) throws ParseException {
        List<FunctionType> list = new LinkedList<>();
        for (Token token : listOper) {
            FunctionType functiontype = FunctionType.parse(token.getText());
            checkNull(functiontype, "Invalid operator: " + token);
            list.add(functiontype);
        } 
        return makeInfixFunc(listExpr, list);
    }
    
    private IExpression makeInfixFunc(List<IExpression> listExpr, List<FunctionType> listFunc) throws ParseException {
        if (listExpr.size() != listFunc.size() + 1)
            throw new ParseException("Invalid infix expression, expressions: " + listExpr.size() + ", operators: " + listFunc.size()); 
        if (listExpr.size() == 1)
            return listExpr.get(0); 
        int i = Integer.MAX_VALUE;
        int j = Integer.MIN_VALUE;
        for (FunctionType functiontype : listFunc) {
            i = Math.min(functiontype.getPrecedence(), i);
            j = Math.max(functiontype.getPrecedence(), j);
        } 
        if (j >= i && j - i <= 10) {
            for (int k = j; k >= i; k--)
                mergeOperators(listExpr, listFunc, k); 
            if (listExpr.size() == 1 && listFunc.size() == 0)
                return listExpr.get(0); 
            throw new ParseException("Error merging operators, expressions: " + listExpr.size() + ", operators: " + listFunc.size());
        } 
        throw new ParseException("Invalid infix precedence, min: " + i + ", max: " + j);
    }
    
    private void mergeOperators(List<IExpression> listExpr, List<FunctionType> listFuncs, int precedence) throws ParseException {
        for (int i = 0; i < listFuncs.size(); i++) {
            FunctionType functiontype = listFuncs.get(i);
            if (functiontype.getPrecedence() == precedence) {
                listFuncs.remove(i);
                IExpression iexpression = listExpr.remove(i);
                IExpression iexpression1 = listExpr.remove(i);
                IExpression iexpression2 = makeFunction(functiontype, new IExpression[] { iexpression, iexpression1 });
                listExpr.add(i, iexpression2);
                i--;
            } 
        } 
    }
    
    private IExpression parseExpression(Deque<Token> deque) throws ParseException {
        FunctionType type, operType;
        Token token = deque.poll();
        checkNull(token, "Missing expression");
        switch (token.getType()) {
            case NUMBER:
                return makeConstantFloat(token);
            case IDENTIFIER:
                type = getFunctionType(token, deque);
                if (type != null)
                    return makeFunction(type, deque); 
                return makeVariable(token);
            case BRACKET_OPEN:
                return makeBracketed(token, deque);
            case OPERATOR:
                operType = FunctionType.parse(token.getText());
                checkNull(operType, "Invalid operator: " + token);
                if (operType == FunctionType.PLUS)
                    return parseExpression(deque); 
                if (operType == FunctionType.MINUS) {
                    IExpression exprNeg = parseExpression(deque);
                    return makeFunction(FunctionType.NEG, new IExpression[] { exprNeg });
                } 
                if (operType == FunctionType.NOT) {
                    IExpression exprNot = parseExpression(deque);
                    return makeFunction(FunctionType.NOT, new IExpression[] { exprNot });
                } 
                break;
        } 
        throw new ParseException("Invalid expression: " + token);
    }
    
    private static IExpression makeConstantFloat(Token token) throws ParseException {
        float f = Config.parseFloat(token.getText(), Float.NaN);
        if (f == Float.NaN)
            throw new ParseException("Invalid float value: " + token); 
        return new ConstantFloat(f);
    }
    
    private FunctionType getFunctionType(Token token, Deque<Token> deque) throws ParseException {
        Token tokenNext = deque.peek();
        if (tokenNext != null && tokenNext.getType() == TokenType.BRACKET_OPEN) {
            FunctionType functionType = FunctionType.parse(token.getText());
            checkNull(functionType, "Unknown function: " + token);
            return functionType;
        } 
        FunctionType type = FunctionType.parse(token.getText());
        if (type == null)
            return null; 
        if (type.getParameterCount(new IExpression[0]) > 0)
            throw new ParseException("Missing arguments: " + type); 
        return type;
    }
    
    private IExpression makeFunction(FunctionType type, Deque<Token> deque) throws ParseException {
        if (type.getParameterCount(new IExpression[0]) == 0)
            return makeFunction(type, new IExpression[0]); 
        Token token = deque.poll();
        Deque<Token> deque1 = getGroup(deque, TokenType.BRACKET_CLOSE, true);
        IExpression[] aiexpression = parseExpressions(deque1);
        return makeFunction(type, aiexpression);
    }
    
    private IExpression[] parseExpressions(Deque<Token> deque) throws ParseException {
        List<IExpression> list = new ArrayList<>();
        while (true) {
            Deque<Token> deque1 = getGroup(deque, TokenType.COMMA, false);
            IExpression iexpression = parseInfix(deque1);
            if (iexpression == null) {
                IExpression[] aiexpression = list.<IExpression>toArray(new IExpression[list.size()]);
                return aiexpression;
            } 
            list.add(iexpression);
        } 
    }
    
    private static IExpression makeFunction(FunctionType type, IExpression[] args) throws ParseException {
        ExpressionType[] aexpressiontype = type.getParameterTypes(args);
        if (args.length != aexpressiontype.length)
            throw new ParseException("Invalid number of arguments, function: \"" + type.getName() + "\", count arguments: " + args.length + ", should be: " + aexpressiontype.length); 
        for (int i = 0; i < args.length; i++) {
            IExpression iexpression = args[i];
            ExpressionType expressiontype = iexpression.getExpressionType();
            ExpressionType expressiontype1 = aexpressiontype[i];
            if (expressiontype != expressiontype1)
                throw new ParseException("Invalid argument type, function: \"" + type.getName() + "\", index: " + i + ", type: " + expressiontype + ", should be: " + expressiontype1); 
        } 
        if (type.getExpressionType() == ExpressionType.FLOAT)
            return new FunctionFloat(type, args); 
        if (type.getExpressionType() == ExpressionType.BOOL)
            return new FunctionBool(type, args); 
        throw new ParseException("Unknown function type: " + type.getExpressionType() + ", function: " + type.getName());
    }
    
    private IExpression makeVariable(Token token) throws ParseException {
        if (this.expressionResolver == null)
            throw new ParseException("Model variable not found: " + token); 
        IExpression iexpression = this.expressionResolver.getExpression(token.getText());
        if (iexpression == null)
            throw new ParseException("Model variable not found: " + token); 
        return iexpression;
    }
    
    private IExpression makeBracketed(Token token, Deque<Token> deque) throws ParseException {
        Deque<Token> deque1 = getGroup(deque, TokenType.BRACKET_CLOSE, true);
        return parseInfix(deque1);
    }
    
    private static Deque<Token> getGroup(Deque<Token> deque, TokenType tokenTypeEnd, boolean tokenEndRequired) throws ParseException {
        Deque<Token> deque1 = new ArrayDeque<>();
        int i = 0;
        Iterator<Token> iterator = deque1.iterator();
        while (iterator.hasNext()) {
            Token token = iterator.next();
            iterator.remove();
            if (i == 0 && token.getType() == tokenTypeEnd)
                return deque1; 
            deque1.add(token);
            if (token.getType() == TokenType.BRACKET_OPEN)
                i++; 
            if (token.getType() == TokenType.BRACKET_CLOSE)
                i--; 
        } 
        if (tokenEndRequired)
            throw new ParseException("Missing end token: " + tokenTypeEnd); 
        return deque1;
    }
    
    private static void checkNull(Object obj, String message) throws ParseException {
        if (obj == null)
            throw new ParseException(message); 
    }
}
