/*
   JMeld is a visual diff and merge tool.
   Copyright (C) 2007  Kees Kuip
   This library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 2.1 of the License, or (at your option) any later version.
   This library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.
   You should have received a copy of the GNU Lesser General Public
   License along with this library; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA  02110-1301  USA
 */
package org.jmeld.ui;

import org.jmeld.ui.*;
import org.jmeld.ui.search.*;
import org.jmeld.ui.swing.*;
import org.jmeld.ui.util.*;
import org.jmeld.util.*;

import javax.swing.*;

abstract public class AbstractBarDialog
    extends JPanel
{
  // Instance variables:
  private JMeldPanel meldPanel;

  public AbstractBarDialog(JMeldPanel meldPanel)
  {
    this.meldPanel = meldPanel;

    init();
  }

  abstract protected void init();

  abstract protected void _activate();

  abstract protected void _deactivate();

  protected JMeldPanel getMeldPanel()
  {
    return meldPanel;
  }

  final public void activate()
  {
    _activate();
  }

  final public void deactivate()
  {
    _deactivate();
  }
}
