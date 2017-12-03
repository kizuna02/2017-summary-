/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDocSource extends DocSource {

    protected ArrayList<File> _Files = new ArrayList<>();
 
    public FileDocSource(String location) {
      this._Files.addAll(FileFinder.GetAllFiles(location));
    }

    @Override
    public int getNumDocs() {
       return this._Files.size();
    }
    


    @Override
    public String getDoc(int id) {
         StringBuilder sb = new StringBuilder();
        File file = this._Files.get(id);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line = br.readLine();
            while(line != null){
                sb.append(line);
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileDocSource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileDocSource.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    
        return sb.toString();
    }

    
}
