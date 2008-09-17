package org.jmeld.util.vc.svn;

import org.jmeld.util.vc.*;

import java.io.*;

public class SubversionVersionControl
       implements VersionControlIF
{
  public BlameIF getBlame(File file)
  {
    BlameCmd cmd;

    cmd = new BlameCmd(file);
    cmd.execute();
    return cmd.getResultData();
  }

  public DiffIF getDiff(File file, boolean recursive)
  {
    DiffCmd cmd;

    cmd = new DiffCmd(file);
    cmd.execute();
    return cmd.getResultData();
  }
}
