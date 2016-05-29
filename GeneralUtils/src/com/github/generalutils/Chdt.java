package com.github.generalutils;



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.util.*;
import java.text.*;

import javax.swing.*;

public class Chdt{
    static File file;
    static JFrame frame = new JFrame("Input a file to change");
    public static void main(String[] args) {
        try{

            final JFileChooser fc = new JFileChooser();
            fc.setMultiSelectionEnabled(false);

            //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            //System.out.println("Enter file name with extension:");
            //String str = bf.readLine();
            JOptionPane.showMessageDialog(null, "Input a file to change.");
            frame.setSize(300, 200);

            frame.setVisible(true);

            int retVal = fc.showOpenDialog(frame);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                frame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "3RR0RZ!  You didn't input a file.");
                System.exit(0);
            }

            //System.out.println("Enter last modified date in 'dd-mm-yyyy-hh-mm-ss' format:");
            //String strDate = bf.readLine();
            String strDate = JOptionPane.showInputDialog("Enter created date in 'dd-mm-yyyy-hh-mm-ss' format:");

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
            Date createdDate = sdf.parse(strDate);
            
            
            strDate = JOptionPane.showInputDialog("Enter last modified date in 'dd-mm-yyyy-hh-mm-ss' format:");
            Date modifiedDate = sdf.parse(strDate);
            
            strDate = JOptionPane.showInputDialog("Enter last accessed date in 'dd-mm-yyyy-hh-mm-ss' format:");
            Date accessedDate = sdf.parse(strDate);
            
            
            if (file.exists()){
                //file.setLastModified(date.getTime());
            	setFileCreationDate(createdDate, modifiedDate, accessedDate);
                JOptionPane.showMessageDialog(null, "Modification is successful!");
            }
            else{
                JOptionPane.showMessageDialog(null, "File does not exist!  Did you accidentally it or what?");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "3RR0RZ");
        }
    }

    public static void setFileCreationDate(Date creationDate, Date modifiedDate, Date accessedDate) throws IOException{

        BasicFileAttributeView attributes = Files.getFileAttributeView(Paths.get(file.getPath()), BasicFileAttributeView.class);
        FileTime createdTime = FileTime.fromMillis(creationDate.getTime());
        FileTime modifiedTime = FileTime.fromMillis(modifiedDate.getTime());
        FileTime accessedTime = FileTime.fromMillis(accessedDate.getTime());
        attributes.setTimes(createdTime, modifiedTime, accessedTime);

    }
    
}