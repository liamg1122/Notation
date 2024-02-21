/**
 * This notation class has methods to convert infix to postfix, 
 * postfix to infix, and evaluate postfix expressions. 
 * 
 * @author Liam Ghershony
 */

public class Notation {
	

    /**
     * Converts infix to postfix.
     * 
     * @param infix -- The infix expression that will be converted.
     * @return postfix -- the converted postfix expression.
     * @throws InvalidNotationFormatException -- if the infix is not in good format, such as bad operator placement or mismatched parentheses.
     */
	
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		MyQueue<Character> postFixQueue = new MyQueue<Character>(35);
		MyStack<Character> postFixStack = new MyStack<Character>(35);
		
		for (int i=0; i<infix.length(); i++) {
			char characterThis = infix.charAt(i);
			if(characterThis ==' ') {
				continue;
			}
			else if(Character.isDigit(characterThis)) {
				try {
					postFixQueue.enqueue(characterThis);
				} catch (QueueOverflowException e) {
					e.getMessage();
				}
				
			}
			else if(characterThis == '(') {
				try {
					postFixStack.push(characterThis);
				} catch (StackOverflowException e) {
					e.getMessage();
				}
			}
			else if(operator(characterThis)) {
				try {
					while(!postFixStack.isEmpty() && precedence(postFixStack.top())>= precedence(characterThis)) {
					
						try {
							postFixQueue.enqueue(postFixStack.pop());
						} catch (QueueOverflowException e) {
							e.getMessage();
						}
					}
				} catch (IllegalOperatorException e) {
					e.getMessage();
				} catch (StackUnderflowException e) {
					e.getMessage();
				}
				
				
				try {
					postFixStack.push(characterThis);
				} catch (StackOverflowException e) {
					e.getMessage();
				}
				
			}
			
			else if(characterThis == ')') {
				try {
					while(!postFixStack.isEmpty() && postFixStack.top() != '(') {
						postFixQueue.enqueue(postFixStack.pop());
					}
				} catch (StackUnderflowException e) {
					e.getMessage();
				} catch (QueueOverflowException e) {
					e.getMessage();
				}
			
					if(postFixStack.isEmpty()) {
						throw new InvalidNotationFormatException("Bad parentheses!");}
					
		
				
				try {
					if(!postFixStack.isEmpty() && postFixStack.top() == '(') {
						postFixStack.pop();
					}
				} catch (StackUnderflowException e) {
					e.getMessage();
				}
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		while(!postFixQueue.isEmpty()) {
			try {
				sb.append(postFixQueue.dequeue());
			} catch (QueueUnderflowException e) {
				e.getMessage();
			}
		}
		
		return sb.toString();
		
	}
	
	
	private static int precedence(char pr) throws IllegalOperatorException {
		switch(pr) {
		case '-':
		case '+':
			return 1;
		case '*':
		case '/':
			return 2;
		default:
			throw new IllegalOperatorException("Illegal operator" +pr);
		
		}
	}
	
	 /**
     * Converts postfix to infix.
     * 
     * @param postfix -- the postfix to be converted.
     * @return infix -- the returned infix.
     * @throws InvalidNotationFormatException -- if postfix is not properly formatted.
     */

	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
	    MyStack<String> stack = new MyStack<>();

	    for (int i = 0; i < postfix.length(); i++) {
	        char ch = postfix.charAt(i);

	        if (Character.isDigit(ch)) {
	            try {
					stack.push(String.valueOf(ch));
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
	        } else if (operator(ch)) {
	            if (stack.size() < 2) {
	                throw new InvalidNotationFormatException("Invalid postfix notation: Not enough operands for operator " + ch);
	            }

	            try {
	                String op2 = stack.pop();
	                String op1 = stack.pop();
	                String expr = "(" + op1 + ch + op2 + ")";
	                try {
						stack.push(expr);
					} catch (StackOverflowException e) {
						e.printStackTrace();
					}
	            } catch (StackUnderflowException e) {
	                throw new InvalidNotationFormatException("Error during conversion: " + e.getMessage());
	            }
	        }
	    }

	    if (stack.size() != 1) {
	        throw new InvalidNotationFormatException("Invalid postfix notation: More than one item left on the stack");
	    }

	    try {
	        return stack.pop();
	    } catch (StackUnderflowException e) {
	        throw new InvalidNotationFormatException("Error retrieving final result: " + e.getMessage());
	    }
	}


	 /**
     * Evaluates postfix and returns value as double
     * 
     * @param postfix -- the postfix to be evaluated.
     * @return Double value of the postfix.
     * @throws InvalidNotationFormatException -- if the postfix is badly formatted.
     */
	
	public static double evaluatePostfixExpression(String postfix) throws InvalidNotationFormatException {
	    MyStack<Double> evaluation = new MyStack<>();
	    double op1 = 0;
	    double op2 = 0;

	    for (int i = 0; i < postfix.length(); i++) {
	        char c = postfix.charAt(i);

	        if (c == ' ') {
	            continue;
	        } else if (Character.isDigit(c)) {
	            try {
					evaluation.push((double) (c - '0'));
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
	        } else if (operator(c)) {
	            if (evaluation.size() < 2) {
	                throw new InvalidNotationFormatException("Invalid expression: Not enough operands before operator " + c);
	            }

	            try {
	                op2 = evaluation.pop();
	                op1 = evaluation.pop();
	            } catch (StackUnderflowException e) {
	                throw new InvalidNotationFormatException("Error during evaluation: " + e.getMessage());
	            }

	            double result;
	            switch (c) {
	                case '+':
	                    result = op1 + op2;
	                    break;
	                case '*':
	                    result = op1 * op2;
	                    break;
	                case '-':
	                    result = op1 - op2;
	                    break;
	                case '/':
	                    if (op2 == 0) {
	                        throw new InvalidNotationFormatException("Dividing by zero is not allowed.");
	                    }
	                    result = op1 / op2;
	                    break;
	                default:
	                    throw new InvalidNotationFormatException("Invalid operator: " + c);
	            }
	            try {
					evaluation.push(result);
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
	        } else {
	            throw new InvalidNotationFormatException("Invalid character: " + c);
	        }
	    }

	    if (evaluation.size() != 1) {
	        throw new InvalidNotationFormatException("Invalid postfix notation: Incorrect number of items in the stack after evaluation.");
	    }

	    try {
	        return evaluation.pop();
	    } catch (StackUnderflowException e) {
	        throw new InvalidNotationFormatException("Error retrieving the final evaluation result: " + e.getMessage());
	    }
	}

	 /**
     * Method to determine if operator is valid.
     * 
     * @param op -- the character to be checked
     * @return true if the character is a valid operator.
     */
	
	public static boolean operator(char op) {
		
		return op == '+' || op == '-' || op =='*' || op == '/';
	}
}
		
		
	
		
	


