/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import wig.analysis.*;

public final class TSchema extends Token
{
    public TSchema()
    {
        super.setText("schema");
    }

    public TSchema(int line, int pos)
    {
        super.setText("schema");
        setLine(line);
        setPos(pos);
    }

    public Object clone()
    {
      return new TSchema(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTSchema(this);
    }

    public void setText(String text)
    {
        throw new RuntimeException("Cannot change TSchema text.");
    }
}
