
package linkedlist;

public class StudentList {
     Nodestudent head;
        private int counter;
        
        
          public Nodestudent findStudent(int studentId) {
            Nodestudent current = head;
            while (current != null) {
                if (current.studentid == studentId)   return current; // Found
                current = current.next; // Move to next node
            }
            return null; // Not found after traversing entire list
        }

        
        public void addstudent(int id){
            Nodestudent newnode =new Nodestudent(id);
            //check if list is empty
            if(head==null){
                head =newnode;
            }else{            
                Nodestudent current;
                current=head;
                while (current.next !=null) {
                    current =current.next;//move to the next node
                }current.next =newnode; //node will add to the StudentList
            }            
        }
        public void removestudent(int id){
            //check if node locate in the first
            if (head.studentid ==id) {
                head =head.next;//delete the first node
            }
            Nodestudent current =head;
            while (current.next !=null) {
                if (current.next.studentid ==id) {
                    current.next =current.next.next;//the node was deleted
                    return;
                }
                current=current.next;
            }
        } 
        public int laststudent(){
            Nodestudent current =head;
            while (current.next !=null) {
                current =current.next;
            }
            return current.studentid;
        }
        public void display(){
            Nodestudent current=head;
            while (current !=null) {
                System.out.println("student: "+current.studentid);
                current=current.next;
            }
        }
        public boolean searchstudent(int studentID){
        Nodestudent currentstudent=head;
            while (currentstudent!=null) {
                if (currentstudent.studentid==studentID) {
                    return true;
                }
                currentstudent=currentstudent.next;
            }
            return false;
        }
       
   
    
}
