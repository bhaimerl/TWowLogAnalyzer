package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpUpload {

	public static boolean fileUpload(String password, String subdirname, String pathToFile, String fileName) { 
		
		boolean success = false;
		String server = "f28-preview.awardspace.net";
        int port = 21; // Standard-FTP-Port
        String user = "4561924_klarasprudel";
        FTPClient ftpClient = new FTPClient();

        try {
            // Verbindung zum FTP-Server herstellen
            ftpClient.connect(server, port);
            int replyCode = ftpClient.getReplyCode();
            if (!ftpClient.login(user, password)) {
                System.out.println("Login fehlgeschlagen!");
                return success;
            }
            System.out.println("Verbindung hergestellt: " + replyCode);

            // FTP-Modus einstellen
            ftpClient.enterLocalPassiveMode(); // Für Firewalls empfohlen
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            String remoteDir = "klarasprudel.atwebpages.com/"+subdirname;

            if (!ftpClient.changeWorkingDirectory(remoteDir)) {
                boolean dirCreated = ftpClient.makeDirectory(remoteDir);
                if (dirCreated) {
                	ftpClient.changeWorkingDirectory(remoteDir);
                    System.out.println("Verzeichnis erstellt: " + remoteDir);
                } else {
                    System.out.println("Konnte Verzeichnis nicht erstellen.");
                }
            }            
            
            
            
            // Datei zum Hochladen vorbereiten
            File localFile = new File(pathToFile);
            String remoteFile = fileName; // Zielpfad auf dem Server

            try (FileInputStream inputStream = new FileInputStream(localFile)) {
                boolean done = ftpClient.storeFile(remoteFile, inputStream);
                if (done) {
            		success = true;
                    System.out.println("Datei erfolgreich hochgeladen.");
                } else {
                    System.out.println("Fehler beim Hochladen der Datei: " +remoteFile+" in das verzeichnis: "+remoteDir);
                }
            }

            // Verbindung beenden
            ftpClient.logout();
        } catch (IOException ex) {
            System.out.println("Fehler: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		return success;
	}
	
}
