/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import java.util.*;
import wig.analysis.*;

public final class AExpStmNoShortIf extends PStmNoShortIf
{
    private PExp _exp_;
    private TSemicolon _semicolon_;

    public AExpStmNoShortIf()
    {
    }

    public AExpStmNoShortIf(
        PExp _exp_,
        TSemicolon _semicolon_)
    {
        setExp(_exp_);

        setSemicolon(_semicolon_);

    }
    public Object clone()
    {
        return new AExpStmNoShortIf(
            (PExp) cloneNode(_exp_),
            (TSemicolon) cloneNode(_semicolon_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAExpStmNoShortIf(this);
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

    public TSemicolon getSemicolon()
    {
        return _semicolon_;
    }

    public void setSemicolon(TSemicolon node)
    {
        if(_semicolon_ != null)
        {
            _semicolon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _semicolon_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_exp_)
            + toString(_semicolon_);
    }

    void removeChild(Node child)
    {
        if(_exp_ == child)
        {
            _exp_ = null;
            return;
        }

        if(_semicolon_ == child)
        {
            _semicolon_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_exp_ == oldChild)
        {
            setExp((PExp) newChild);
            return;
        }

        if(_semicolon_ == oldChild)
        {
            setSemicolon((TSemicolon) newChild);
            return;
        }

    }
}
