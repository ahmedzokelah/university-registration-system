
package linkedlist;

public class CourseList {
           Nodecourse head;
        int counter=0;
        
        
             
        public Nodecourse findCourse(int courseId) {
            Nodecourse current = head;
            while (current != null) {
                if (current.courseid == courseId)   return current; // Found
                current = current.next; // Move to next node
            }
            return null;       // Not found after traversing entire list
        }

        public boolean searchCourse(int courseid) {
            Nodecourse current = head;

            while (current != null) {
                if (current.courseid == courseid)   return true; // Found
                current = current.next; // Move to next node
            }
            return false; // Not found after traversing entire list
        }
        public void addcourse(int id){
            Nodecourse newnode=new Nodecourse(id);
            if(counter>30)
                System.out.println("the courese already has 30 student not allowed to add more");
            else if(head==null){
                head=newnode;
                newnode.next=null;
                counter++;
             }
            else{
                Nodecourse current=head;
                while (current.next!=null) {
                    current=current.next;
                }
                 current.next=newnode;
                counter++;
            }
        }
        public void removecourse(int id){
            if (head.courseid ==id) {
                head =head.next;
            }
            Nodecourse current =head;
            while (current.next !=null) {
                if (current.next.courseid==id) {
                    current.next =current.next.next;//the node was deleted
                    counter--;
                    return;
                }
                current=current.next;
            }

        }
        public int lastcourse(){
            Nodecourse current =head;
            while (current.next !=null) {
                current=current.next;
            }return current.courseid;
        }
        public void display(){
            Nodecourse current =head;
            while (current !=null) {
                System.out.println("course: "+current.courseid);
                current =current.next;
            }
        }
      
        
}
