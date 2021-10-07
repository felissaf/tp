package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniquePersonList;
import seedu.address.model.task.Task;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private List<Task> tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tasks = new List<Task>() {
        };
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Student> students) {
        this.persons.setPersons(students);
    }

    /**
     * Replaces the contents of the student list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasPerson(Student student) {
        requireNonNull(student);
        return persons.contains(student);
    }

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }


    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addPerson(Student p) {
        persons.add(p);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }


    /**
     * Replaces the given student {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedPerson} must not be the same
     * as another existing student in the address book.
     */
    public void setPerson(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        persons.setPerson(target, editedStudent);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The task identity of {@code editedTask} must not be the same
     * as another existing task in the module.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Student key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from this {@code Module}.
     * {@code key} must exist in the module.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && tasks.equals(((AddressBook) other).tasks));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
