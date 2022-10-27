package sandwich.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {

    /*
    @Pointcut("execution(* sandwich.service.*.find*(..))")
    public void executeFind() { }
    */

    @Pointcut("execution(* sandwich.model.Course.getCourseParticipant*(..))")
    public void executeGetCourseParticipant() {
  //      System.out.println("PointCut called");
    }

    @Pointcut("execution(* sandwich.model.*.getSession*(..))")
    public void executeGetSession() { }

    @Pointcut("execution(* sandwich.repository.*.write(..))")
    public void executeWriteToFile() { }

    @Pointcut("execution(* sandwich.repository.*.getSandwich*(..))")
    public void executeGetSandwich() { }

    @Pointcut("execution(* sandwich.model.CourseParticipant.orderSandwich(..))")
    public void executeOrderSandwich() { }



}
