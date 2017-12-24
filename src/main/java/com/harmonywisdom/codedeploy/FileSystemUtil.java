package com.harmonywisdom.codedeploy;

public class FileSystemUtil {
	
	public static FileStatus fileStatus(LinuxShell shell,String fileName,String context){
		FileStatus fs=new FileStatus();
		String res=shell.exeCommandWithContextFolderSet("ls -lh "+fileName, context);
		//System.out.println(res);
		String lines[]=res.split("\n");
		for(int i=0;i<lines.length;i++){
			String line=lines[i];
			
			String col[]=line.split("\\s+");
			if(col.length>=8 && col[8].equals(fileName)){
				
				fs.setAccessFlags(col[0]);
				fs.setOwner(col[2]);
				fs.setOwner2(col[3]);
				fs.setSize(col[4]);
				fs.setMongth(col[5]);
				fs.setDay(col[6]);
				fs.setTime(col[7]);
				fs.setFile(col[8]);
				break;
			}
		}

		
		
		return fs;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinuxShell ls=new LinuxShell();
		//ls.setHostName("");
		ls.setIp("103.40.101.98");
		ls.setPassword("zthz123");
		ls.setUserName("root");
		FileStatus fs=fileStatus(ls, "keywords.log", "/home/data/crawler/weibo_client/log");
		System.out.println(fs);
	}

}
