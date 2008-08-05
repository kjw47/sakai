/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright 2006 Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 *       http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *
 **********************************************************************************/

package uk.ac.cam.caret.sakai.rwiki.utils;

/**
 * @author andrew
 */
public class XmlEscaper
{

	public static final char HIGHEST_CHARACTER = '>';

	public static final char[][] specialChars = new char[HIGHEST_CHARACTER + 1][];
	static
	{
		specialChars['>'] = "&gt;".toCharArray();
		specialChars['<'] = "&lt;".toCharArray();
		specialChars['&'] = "&amp;".toCharArray();
		specialChars['"'] = "&#34;".toCharArray();
		specialChars['\''] = "&#39;".toCharArray();
	}

	public static String xmlEscape(String toEscape)
	{
		char[] chars = toEscape.toCharArray();
		int lastEscapedBefore = 0;
		StringBuffer escapedString = null;
		for (int i = 0; i < chars.length; i++)
		{
			if (chars[i] <= HIGHEST_CHARACTER)
			{
				char[] escapedPortion = specialChars[chars[i]];
				if (escapedPortion != null)
				{
					if (lastEscapedBefore == 0)
					{
						escapedString = new StringBuffer(chars.length + 5);
					}
					if (lastEscapedBefore < i)
					{
						escapedString.append(chars, lastEscapedBefore, i
								- lastEscapedBefore);
					}
					lastEscapedBefore = i + 1;
					escapedString.append(escapedPortion);
				}
			}
		}

		if (lastEscapedBefore == 0)
		{
			return toEscape;
		}

		if (lastEscapedBefore < chars.length)
		{
			escapedString.append(chars, lastEscapedBefore, chars.length
					- lastEscapedBefore);
		}

		return escapedString.toString();
	}

}
