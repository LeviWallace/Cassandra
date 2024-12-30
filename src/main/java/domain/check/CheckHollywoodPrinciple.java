package domain.check;

import domain.Issue;
import domain.source.SourceClass;
import domain.source.SourceMethod;

import java.util.ArrayList;
import java.util.List;

public class CheckHollywoodPrinciple extends ClassChecker {

    static final String MESSAGE = "This method violates Hollywood Principle";
    @Override
    protected List<Issue> checkClass(SourceClass object) {
        // Class A: calls a method in Class B, and Class B calls an object in Class A.
        if (object.isInterface())
            return List.of();

        // 1. check all objects that we call methods on.
        List<Issue> issues = new ArrayList<>();
        List<SourceClass> weCall = new ArrayList<>();
        //      1.a. check all our methods.
        for (SourceMethod ourMethod: object.getMethods())
        {
        //      1.b. check all function calls in methods
                for (SourceMethod methodCall: ourMethod.getCalledMethods())
                {
        //      1.c. add those method's classes to weCall
                    weCall.add(methodCall.getAssociatedClass());
                }
        }
        // 2. if that object calls a method on us, add to issue
        for (SourceClass called: weCall)
        {
            for (SourceMethod theirMethod: called.getMethods())
            {
                for (SourceMethod theirMethodCall: theirMethod.getCalledMethods())
                {
                    if (theirMethodCall.getAssociatedClass().equals(object))
                    {
                        return List.of(new Issue(MESSAGE, object));
                    }
                }
            }
        }
        /// 3. else no nothing
        return List.of();
    }
}
