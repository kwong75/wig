/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import wig.analysis.*;

public final class THtmlTagEnd extends Token
{
    public THtmlTagEnd()
    {
        super.setText("</html>");
    }

    public THtmlTagEnd(int line, int pos)
    {
        super.setText("</html>");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new THtmlTagEnd(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTHtmlTagEnd(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change THtmlTagEnd text.");
    }
}
