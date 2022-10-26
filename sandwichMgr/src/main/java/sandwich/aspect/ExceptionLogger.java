package sandwich.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import sandwich.exception.ParticipantNotFoundException;
import sandwich.exception.SandwichNotFoundException;
import sandwich.exception.SessionNotFoundException;

import java.io.IOException;

@Component
@Aspect
public class ExceptionLogger {

    Logger logger = LogManager.getLogger("exceptionLogger");


    @AfterThrowing(pointcut = "sandwich.aspect.PointCuts.executeGetCourseParticipant()", throwing = "e")
    public void afterParticipantNotFoundExceptionIsThrown(ParticipantNotFoundException e) {
 //       System.out.println("ExceptionLogger called");
        logger.error("Participant not found: " + e.getMessage());
    }

    @AfterThrowing(pointcut = "sandwich.aspect.PointCuts.executeGetSession()", throwing = "e")
    public void afterSessionNotFoundException(SessionNotFoundException e) {
        logger.error("Session not found: " + e.getMessage());
    }

    @AfterThrowing(pointcut = "sandwich.aspect.PointCuts.executeWriteToFile()e", throwing = "e")
    public void afterIOFileException(IOException e) {
        logger.error("IO File Exception: " + e.getMessage());
    }

    @AfterThrowing(pointcut = "sandwich.aspect.PointCuts.executeGetSandwich()", throwing = "e")
    public void afterSandwichNotFoundException(SandwichNotFoundException e) {
        logger.error("Sandwich not found or IO File Exception: " + e.getMessage());
    }











}
