
package linkedlist;


public class University {
   
    private StudentList students;
    private CourseList courses;
    private ActionStack undoStack;
    private ActionStack redoStack;
    
    public University(){
        this.students =new StudentList();//store each student to the system
        this.courses =new CourseList();//store each course to the system
        this.undoStack = new ActionStack();
        this.redoStack = new ActionStack();
    }
    
    public void addstudent(int id){
        students.addstudent(id);
         undoStack.push(new Action(Action.ActionType.ADD_STUDENT, id, -1));
        redoStack = new ActionStack(); // Clear redo stack when new action is performed
    }
    public void removestudent(int id){
          if (students.findStudent(id) != null) {
            undoStack.push(new Action(Action.ActionType.REMOVE_STUDENT, id, -1));
            students.removestudent(id);
            redoStack = new ActionStack();
          }
        
    }
    public void addcourse(int id){
        courses.addcourse(id);
         undoStack.push(new Action(Action.ActionType.ADD_COURSE, -1, id));
        redoStack = new ActionStack();
    }
    public void removecourse(int id){
         if (courses.findCourse(id) != null) {
            undoStack.push(new Action(Action.ActionType.REMOVE_COURSE, -1, id));
            courses.removecourse(id);
            redoStack = new ActionStack();
        }
    }
    public void displayallstudents(){
        students.display();
    }
    public void displayallcourses(){
        courses.display();
    }
    public void laststudent(){
        System.out.println("The last student added: "+students.laststudent());
    }
    public void lastcourse(){
        System.out.println("The last course added: "+courses.lastcourse());
    }

    public void enrollment(int studentID, int courseID){

    Nodestudent currentstudent = students.findStudent(studentID);
    Nodecourse currentcourse = courses.findCourse(courseID);
    
    if (currentstudent == null || currentcourse == null) {
        System.out.println("Student or Course not found!");
        return;
    }
    //check if the student register more than 7 courses
        if (currentstudent.courses>=7) {
            System.out.println("This student can't registere more courses");
            return;
        }
      //check if the course has less than 30 student enrolled
        if (currentcourse.enrolledstudents>=30) {
            System.out.println("You can't register this course");
            return;
        }
     //prevent the student from registering the same course
     Nodecourse coursepointer =currentstudent.nextcourse;
        while (coursepointer!=null) {
            if (coursepointer.courseid==courseID) {
                System.out.println("You can't register the same course ");
                return;
            }
            coursepointer=coursepointer.next;
           
        }
    // Add course to student's list
    Nodecourse newCourseNode = new Nodecourse(courseID);
    if (currentstudent.nextcourse == null) {
        currentstudent.nextcourse = newCourseNode;
        
    } else {
        Nodecourse temp = currentstudent.nextcourse;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newCourseNode;
    }
    currentstudent.courses++;
    

    // Add student to course's list
    Nodestudent newStudentNode = new Nodestudent(studentID);
    if (currentcourse.nextstudent == null) {
        currentcourse.nextstudent = newStudentNode;
    } else {
        Nodestudent temp = currentcourse.nextstudent;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudentNode;
    }
    currentcourse.enrolledstudents++;

          undoStack.push(new Action(Action.ActionType.ENROLL, studentID, courseID));
        redoStack = new ActionStack();
    
    
    }
    public void display_student_courses (int studentID){
    Nodestudent currentstudent = students.findStudent(studentID);
    if (currentstudent == null || currentstudent.nextcourse == null) {
        System.out.println("This student has no courses registered.");
        return;
    }
    
    Nodecourse temp = currentstudent.nextcourse;
    while (temp != null) {
        System.out.println("Student " + currentstudent.studentid + " has course " + temp.courseid);
        temp = temp.next;
    }
    
    }
    public void display_course_students(int courseID){
        Nodecourse currentcourse = courses.findCourse(courseID);
    if (currentcourse == null || currentcourse.nextstudent == null) {
        System.out.println("This course has no students.");
        return;
    }
    
    Nodestudent temp = currentcourse.nextstudent;
    while (temp != null) {
        System.out.println("Course " + currentcourse.courseid + " has student " + temp.studentid);
        temp = temp.next;
    }
    }
    
    public void removeEnrollment(int studentID , int courseID){
  Nodestudent currentstudent = students.findStudent(studentID);
    Nodecourse currentcourse = courses.findCourse(courseID);
    
    if (currentstudent == null || currentcourse == null) {
        System.out.println("Student or Course not found!");
        return;
    }

    // Remove course from student's list
    if (currentstudent.nextcourse != null) {
        if (currentstudent.nextcourse.courseid == courseID) {   //check if you want to  remove the first course
            currentstudent.nextcourse = currentstudent.nextcourse.next;
            currentstudent.courses--;
        } else {
            Nodecourse tempCourse = currentstudent.nextcourse;
            while (tempCourse.next != null) {
                if (tempCourse.next.courseid == courseID) {
                    tempCourse.next = tempCourse.next.next;
                    currentstudent.courses--;
                    
                    break;
                }
                tempCourse = tempCourse.next;
            }
        }
    }

    // Remove student from course's list
    if (currentcourse.nextstudent != null) {
        if (currentcourse.nextstudent.studentid == studentID) {
            currentcourse.nextstudent = currentcourse.nextstudent.next;
            currentcourse.enrolledstudents--;
        } else {
            Nodestudent tempStudent = currentcourse.nextstudent;
            while (tempStudent.next != null) {
                if (tempStudent.next.studentid == studentID) {
                    tempStudent.next = tempStudent.next.next;
                    currentcourse.enrolledstudents--;
                    break;
                }
                tempStudent = tempStudent.next;
            }
        }
    }
      undoStack.push(new Action(Action.ActionType.UNENROLL, studentID, courseID));
        redoStack = new ActionStack();
    
    }
    
    public boolean is_full_course(int courseID){
    Nodecourse currentcourse=courses.findCourse(courseID);
        if (currentcourse.enrolledstudents>30) {
            System.out.println("This course is full");
            return true;
        } else{
            System.out.println("This course has "+(30-currentcourse.enrolledstudents)+" free sets");
        return false;
        }
      
    }
    public boolean is_normal_student(int studentID){
    Nodestudent currentstudent=students.findStudent(studentID);
        if (currentstudent.courses<2||currentstudent.courses>7) {
            return false;
        }else{
        return true;
        }
    
    }
  

    
    public void sort_courses_in_student(int studentID) {
    Nodestudent currentstudent = students.findStudent(studentID);
    if (currentstudent == null || currentstudent.nextcourse == null) {
        return; // No student or no courses to sort
    }

    Nodecourse mainhead = currentstudent.nextcourse;
    currentstudent.nextcourse = null; // Detach the original list to build a new sorted one

    while (mainhead != null) {
        // Find the node with the minimum courseid in the remaining list
        Nodecourse minPrev = null; // Previous node of the minimum node
        Nodecourse min = mainhead; // Minimum node
        Nodecourse prev = mainhead; // For traversal
        Nodecourse current = mainhead.next;

        while (current != null) {
            if (current.courseid < min.courseid) {
                min = current;
                minPrev = prev;
            }
            prev = current;
            current = current.next;
        }

        // Remove the min node from the original list
        if (min == mainhead) {
            mainhead = mainhead.next;
        } else {
            minPrev.next = min.next;
        }

        // Append the min node to the new sorted list
        if (currentstudent.nextcourse == null) {
            currentstudent.nextcourse = min;
            min.next = null;
        } else {
            // Find the last node in the new list
            Nodecourse last = currentstudent.nextcourse;
            while (last.next != null) {
                last = last.next;
            }
            last.next = min;
            min.next = null;
        }
    }
}
   public void sort_students_in_course(int courseID) {
    Nodecourse currentcourse = courses.findCourse(courseID);
    if (currentcourse == null || currentcourse.nextstudent == null) {
        return; // No course or no students to sort
    }

    Nodestudent mainhead = currentcourse.nextstudent;
    currentcourse.nextstudent = null; // Detach the original list to build a new sorted one

    while (mainhead != null) {
        // Find the node with the minimum studentID in the remaining list
        Nodestudent minPrev = null; // Previous node of the minimum node
        Nodestudent min = mainhead; // Minimum node
        Nodestudent prev = mainhead; // For traversal
        Nodestudent current = mainhead.next;

        while (current != null) {
            if (current.studentid < min.studentid) {
                min = current;
                minPrev = prev;
            }
            prev = current;
            current = current.next;
        }

        // Remove the min node from the original list
        if (min == mainhead) {
            mainhead = mainhead.next;
        } else {
            minPrev.next = min.next;
        }

        // Append the min node to the new sorted list
        if (currentcourse.nextstudent == null) {
            currentcourse.nextstudent = min;
            min.next = null;
        } else {
            // Find the last node in the new list
            Nodestudent last = currentcourse.nextstudent;
            while (last.next != null) {
                last = last.next;
            }
            last.next = min;
            min.next = null;
        }
    }
}
   public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo");
            return;
        }
        
        Action action = undoStack.pop();
        redoStack.push(action); // Push to redo stack
        
        switch (action.getType()) {
            case ADD_STUDENT:
                students.removestudent(action.getStudentId());
                break;
            case REMOVE_STUDENT:
                students.addstudent(action.getStudentId());
                break;
            case ADD_COURSE:
                courses.removecourse(action.getCourseId());
                break;
            case REMOVE_COURSE:
                courses.addcourse(action.getCourseId());
                break;
            case ENROLL:
                removeEnrollment(action.getStudentId(), action.getCourseId());
                undoStack.pop(); // Remove the action we just added in removeEnrollment
                redoStack.push(new Action(Action.ActionType.ENROLL, action.getStudentId(), action.getCourseId()));
                break;
            case UNENROLL:
                enrollment(action.getStudentId(), action.getCourseId());
                undoStack.pop(); // Remove the action we just added in enrollment
                redoStack.push(new Action(Action.ActionType.UNENROLL, action.getStudentId(), action.getCourseId()));
                break;
        }
    }
    
    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo");
            return;
        }
        
        Action action = redoStack.pop();
        undoStack.push(action); // Push to undo stack
        
        switch (action.getType()) {
            case ADD_STUDENT:
                students.addstudent(action.getStudentId());
                break;
            case REMOVE_STUDENT:
                students.removestudent(action.getStudentId());
                break;
            case ADD_COURSE:
                courses.addcourse(action.getCourseId());
                break;
            case REMOVE_COURSE:
                courses.removecourse(action.getCourseId());
                break;
            case ENROLL:
                enrollment(action.getStudentId(), action.getCourseId());
                undoStack.pop(); // Remove the action we just added in enrollment
                break;
            case UNENROLL:
                removeEnrollment(action.getStudentId(), action.getCourseId());
                undoStack.pop(); // Remove the action we just added in removeEnrollment
                break;
        }
    }
    
    
   
    
}
