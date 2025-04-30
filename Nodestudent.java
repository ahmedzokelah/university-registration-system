
package linkedlist;

public class Nodestudent {
    
    int studentid;
    int courses=0;    
        Nodestudent next;
        Nodecourse nextcourse;
        
        public Nodestudent(int studentid){
            this.studentid =studentid;
            this.next =null;
            this.nextcourse=null;
        }
}

