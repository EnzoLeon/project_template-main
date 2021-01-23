/*
 * Copyright 2020 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.streamsets.interview;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Enzo Leon
 */
public class Main {
	
	private static Directions directions;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String answer = "Y";
		while(answer.equals("Y")) {
			System.out.println("\nExercise 3 - Robot");
			int xmove = askCoordinate(scan, "x coordinate");
			int ymove = askCoordinate(scan, "y coordinate");
			char cp = askPosition(scan);
			char[] ms = askMoveSequence(scan);
			System.out.println("\nFinal position: " + moveRobot(xmove, ymove, cp, ms));
		    do{
		    	System.out.printf("Thanks, do it again? (Y/N): ");
		    	answer = scan.next().trim().toUpperCase();
		    } while (!Pattern.matches("[YN]",answer));
		}
		scan.close();
	}
	
	static private int askCoordinate(Scanner scan, String text) {
	    int move = -1;
	    while (move < 0) {
	        System.out.printf("Please provide a valid %s: ", text);
	        if(scan.hasNextInt())
	        	move = scan.nextInt();
	        else
	        	scan.next();
	    }
	    return move;
	}
	
	static private char askPosition(Scanner scan) {
	    String cp = "";
	    while (!Pattern.matches("[NESWnesw]",cp)) {
	        System.out.printf("Please provide a valid facing position (N, E, S or W): ");
	        cp = scan.next().trim().toUpperCase();
	    }
	    return cp.charAt(0);
	}
	
	static private char[] askMoveSequence(Scanner scan) {
	    String ms = "";
	    while (!Pattern.matches("[LRMlrm]+",ms)) {
	        System.out.printf("Please provide a move sequence of M, L or R (Example: LMLMRMLMM): ");
	        ms = scan.next().trim().toUpperCase();
	    }
	    return ms.toCharArray();
	}


	static private String moveRobot(int x, int y, char cp, char[] movements) {
		directions = new Directions();
		directions.setInicialDirection(cp);

		for(int i=0; i<movements.length; i++) {
			if(movements[i]=='M') {
				x = Math.max(0, x + directions.getXMove());
				y = Math.max(0, y + directions.getYMove());
			}else {
				cp = directions.changeOrientation(movements[i]);
			}
		}
		return "x:" + x + " y:" + y + " cp:" + cp + "\n";
	}
		
	
}
