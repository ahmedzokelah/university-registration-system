
package linkedlist;

public class Action {
    
    


    public enum ActionType {
        ENROLL, UNENROLL, ADD_STUDENT, REMOVE_STUDENT, ADD_COURSE, REMOVE_COURSE
    }

    private ActionType type;
    private int studentId;
    private int courseId;

    public Action(ActionType type, int studentId, int courseId) {
        this.type = type;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public ActionType getType() {
        return type;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }
}
    

