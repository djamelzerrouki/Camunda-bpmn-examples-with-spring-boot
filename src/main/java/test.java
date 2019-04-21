import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

 
 
 
public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	/*	File src = new File("C:\\Users\\djamel\\Desktop\\emploi2");
		File dest = new File("C:\\Users\\djamel\\Desktop\\emploi3");
		Path srcPath = src.toPath();
		Path destPath = dest.toPath();
		Files.walkFileTree(srcPath, new CopyDirVisitor(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING));
*/ 
	SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
	String inputString1 = "01 04 2019";
	String inputString2 = "01 05 2019";

	try {
	    Date date1 = myFormat.parse(inputString1);
	    Date date2 = myFormat.parse(inputString2);
	    Date date3 =new Date();
	    long diff = date3.getTime() - date1.getTime();
	    System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	
	}



	public static class CopyDirVisitor extends SimpleFileVisitor<Path>
	{
	    private final Path fromPath;
	    private final Path toPath;
	    private final CopyOption copyOption;

	    public CopyDirVisitor(Path fromPath, Path toPath, CopyOption copyOption)
	    {
	        this.fromPath = fromPath;
	        this.toPath = toPath;
	        this.copyOption = copyOption;
	    }

	    @Override
	    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
	    {
	        Path targetPath = toPath.resolve(fromPath.relativize(dir));
	        if( !Files.exists(targetPath) )
	        {
	            Files.createDirectory(targetPath);
	        }
	        return FileVisitResult.CONTINUE;
	    }

	    @Override
	    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
	    {
	        Files.copy(file, toPath.resolve(fromPath.relativize(file)), copyOption);
	        return FileVisitResult.CONTINUE;
	    }
	}

}
