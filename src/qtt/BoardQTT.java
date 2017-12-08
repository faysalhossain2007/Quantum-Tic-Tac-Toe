/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */                                                                                                                                                                                                                                                                                                                                                                                                                                            
package qtt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author Arif
 */
public class BoardQTT {
    int N;
    public boolean[] subscript; // also defines player X or O as X==odd and O==even always
    public boolean[] owner; //index determines owner X or O in the box, also the owner's final subscript
    public JButton cellJButton;
    public int id;
    public int winner=0; // value 1== X, 2==O
    
    public BoardQTT(int N){
        this.N = N;
        subscript = new boolean[N*N+1];
        owner = new boolean[N*N+1];
        
        //System.out.println(" Value of N - "+N);
    }
    
    public int getN(){
        return N;
    }
    
    public int getOwnerSubscript(){
        for(int i=1;i<=N*N;i++){
            if(owner[i]){
                return i;
            }
        }
        return 0;
    }
    
    
}

