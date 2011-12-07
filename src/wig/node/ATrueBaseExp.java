/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import java.util.*;
import wig.analysis.*;

public final class ATrueBaseExp extends PBaseExp
{
    private TTrue _true_;

    public ATrueBaseExp()
    {
    }

    public ATrueBaseExp(
        TTrue _true_)
    {
        setTrue(_true_);

    }
    public Object clone()
    {
        return new ATrueBaseExp(
            (TTrue) cloneNode(_true_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseATrueBaseExp(this);
    }

    public TTrue getTrue()
    {
        return _true_;
    }

    public void setTrue(TTrue node)
    {
        if(_true_ != null)
        {
            _true_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _true_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_true_);
    }

    void removeChild(Node child)
    {
        if(_true_ == child)
        {
            _true_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_true_ == oldChild)
        {
            setTrue((TTrue) newChild);
            return;
        }

    }
}