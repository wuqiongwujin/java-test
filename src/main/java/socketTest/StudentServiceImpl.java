package socketTest;

/**
 * @author Cain
 * @Description
 * @date 2018/11/8
 */
public class StudentServiceImpl implements  StudentService {
    @Override
    public Student getStudent(String name) {
        Student student = new Student();
        student.setName(name);
        student.setAge(18);
        return student;
    }
}
