package database;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class Node {
    @Id
    private String name;
    private Node[] children;
    private Integer value;


    public Node(){}

    public Node(String name, Node[] children,Integer value) {
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public Node setName(String name) {
        this.name = name;
        return this;
    }

    public Node[] getChildren() {
        return children;
    }

    public Node setChildren(Node[] children) {
        this.children = children;
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", children=" + Arrays.toString(children) +
                '}';
    }
}
