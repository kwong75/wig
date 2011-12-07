/* This file was generated by SableCC (http://www.sablecc.org/). */

package wig.node;

import java.util.*;
import wig.analysis.*;

public final class AManyExps extends PExps
{
    private PExps _exps_;
    private TComma _comma_;
    private PExp _exp_;

    public AManyExps()
    {
    }

    public AManyExps(
        PExps _exps_,
        TComma _comma_,
        PExp _exp_)
    {
        setExps(_exps_);

        setComma(_comma_);

        setExp(_exp_);

    }
    public Object clone()
    {
        return new AManyExps(
            (PExps) cloneNode(_exps_),
            (TComma) cloneNode(_comma_),
            (PExp) cloneNode(_exp_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAManyExps(this);
    }

    public PExps getExps()
    {
        return _exps_;
    }

    public void setExps(PExps node)
    {
        if(_exps_ != null)
        {
            _exps_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _exps_ = node;
    }

    public TComma getComma()
    {
        return _comma_;
    }

    public void setComma(TComma node)
    {
        if(_comma_ != null)
        {
            _comma_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _comma_ = node;
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
            + toString(_exps_)
            + toString(_comma_)
            + toString(_exp_);
    }

    void removeChild(Node child)
    {
        if(_exps_ == child)
        {
            _exps_ = null;
            return;
        }

        if(_comma_ == child)
        {
            _comma_ = null;
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
        if(_exps_ == oldChild)
        {
            setExps((PExps) newChild);
            return;
        }

        if(_comma_ == oldChild)
        {
            setComma((TComma) newChild);
            return;
        }

        if(_exp_ == oldChild)
        {
            setExp((PExp) newChild);
            return;
        }

    }
}