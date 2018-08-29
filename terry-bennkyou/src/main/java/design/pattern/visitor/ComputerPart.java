package design.pattern.visitor;

public interface ComputerPart {
    void accept(ComputerPartVisitor computerPartVisitor);
}
