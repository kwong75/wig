/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import wig.analysis.*;

public final class TInput extends Token
{
    public TInput()
    {
        super.setText("input");
    }

    public TInput(int line, int pos)
    {
        super.setText("input");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TInput(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTInput(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TInput text.");
    }
}
