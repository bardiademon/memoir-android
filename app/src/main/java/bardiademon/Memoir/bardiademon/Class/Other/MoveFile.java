package bardiademon.Memoir.bardiademon.Class.Other;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import bardiademon.Memoir.bardiademon.Class.BeforeRunActivity.G;

public class MoveFile
{
    public static boolean Copy (InputStream inputStream, String dirOutPut, String nameFile)
    {
        boolean mkDir = true;

        File fDirOutPut = new File(dirOutPut);
        if (!fDirOutPut.exists())
            mkDir = fDirOutPut.mkdirs();

        if (mkDir)
        {
            boolean resultCopy = false;

            String finalFile = dirOutPut + "/" + nameFile;

            try
            {
                OutputStream outputStream = new FileOutputStream(new File(finalFile));


                int len;
                byte[] buffer = new byte[1024];
                while ((len = inputStream.read(buffer)) > 0)
                    outputStream.write(buffer, 0, len);

                outputStream.flush();

                inputStream.close();
                outputStream.close();
                resultCopy = true;
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return resultCopy;
        }
        else return false;
    }

    private static boolean CheckExistsFile (String nameDir, String nameFile, boolean mkDirs)
    {
        File fDir = new File(nameDir);
        return (!fDir.exists() && mkDirs && !fDir.mkdirs());
    }
}
