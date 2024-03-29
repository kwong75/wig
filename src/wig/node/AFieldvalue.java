/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import java.util.*;
import wig.analysis.*;

public final class AFieldvalue extends PFieldvalue
{
    private TIdentifier _identifier_;
    private TAssign _assign_;
    private PExp _exp_;

    public AFieldvalue()
    {
    }

    public AFieldvalue(
        TIdentifier _identifier_,
        TAssign _assign_,
        PExp _exp_)
    {
        setIdentifier(_identifier_);

        setAssign(_assign_);

        setExp(_exp_);

    }
    public Object clone()
    {
        return new AFieldvalue(
            (TIdentifier) cloneNode(_identifier_),
            (TAssign) cloneNode(_assign_),
            (PExp) cloneNode(_exp_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFieldvalue(this);
    }

    public TIdentifier getIdentifier()
    {
        return _identifier_;
    }

    public void setIdentifier(TIdentifier node)
    {
        if(_identifier_ != null)
        {
            _identifier_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _identifier_ = node;
    }

    public TAssign getAssign()
    {
        return _assign_;
    }

    public void setAssign(TAssign node)
    {
        if(_assign_ != null)
        {
            _assign_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _assign_ = node;
    }

    public PExp getExp()
    {
        return _exp_;
    }

    public void setExp(PExp node)
    {
        if(_exp_ != null)
        {
            _exp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _exp_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_identifier_)
            + toString(_assign_)
            + toString(_exp_);
    }

    void removeChild(Node child)
    {
        if(_identifier_ == child)
        {
            _identifier_ = null;
            return;
        }

        if(_assign_ == child)
        {
            _assign_ = null;
            return;
        }

        if(_exp_ == child)
        {
            _exp_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

        if(_assign_ == oldChild)
        {
            setAssign((TAssign) newChild);
            return;
        }

        if(_exp_ == oldChild)
        {
            setExp((PExp) newChild);
            return;
        }

    }
}
