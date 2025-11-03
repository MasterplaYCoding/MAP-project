package View;

import Model.ADT.Dictionary.MyDictionary;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.List.MyList;
import Model.ADT.Stack.MyIStack;
import Model.ADT.Stack.MyStack;
import Model.Exception.ADTExceptions.EmptyStackException;
import Model.Exception.ADTExceptions.IndexOutOfBoundsException;
import Model.Exception.ADTExceptions.NullKeyException;
import Model.Exception.ExpressionsEvaluation.ValueTypeError;
import Model.Exception.ExpressionsEvaluation.VariableAlreadyDeclared;
import Model.Exception.MyException;
import Model.Expression.ArithExp;
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.Other.PrgState;
import Model.Stmt.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;
import Repository.MemoryRepository;
import ViewModel.Controller;

import java.util.Scanner;

public class UI {
    private Controller controller;

    public UI() {
        MemoryRepository memoryRepository = new MemoryRepository();
        this.controller = new Controller(memoryRepository);
    }

    public UI(Controller controller) {
        this.controller = controller;
    }

    public void Menu() {
        System.out.println("Menu");
        System.out.println("------------------------------");
        System.out.println("1. Input a program");
        System.out.println("2. Complete execution");
        System.out.println("3. Exit");
        System.out.println("-----------------------------");
        System.out.println("Type an option : ");
    }

    public void InputProgram() {
        System.out.println("1. Choose a program from the database");
        System.out.println("2. Input a program manually");
        System.out.println("3. Exit");
        System.out.println("-----------------------------");
        System.out.println("Type a option : ");
    }

    public void DataBase() {
        System.out.println("1. ex1");
        System.out.println("2. ex2");
        System.out.println("3. ex3");
        System.out.println("-----------------------------");
        System.out.println("Type a option : ");
    }

    public void loadEx1() throws MyException {
        MyIStack<IStmt> stk = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl = new MyDictionary<String,Value>();
        MyIList<Value> out = new MyList<Value>();
        IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new
                        VarExp("v"))));
        PrgState state= new PrgState(stk, symtbl, out, ex1);
        MyList<PrgState> states = new MyList<>();
        states.add(state);
        MemoryRepository repository = new MemoryRepository(states);
        this.controller = new Controller(repository);
    }

    public void loadEx2() throws MyException {
        MyIStack<IStmt> stk = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl = new MyDictionary<String,Value>();
        MyIList<Value> out = new MyList<Value>();
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
                                ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        PrgState state= new PrgState(stk, symtbl, out, ex2);
        MyList<PrgState> states = new MyList<>();
        states.add(state);
        MemoryRepository repository = new MemoryRepository(states);
        this.controller = new Controller(repository);
    }

    public void loadEx3() throws MyException {
        MyIStack<IStmt> stk = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl = new MyDictionary<String,Value>();
        MyIList<Value> out = new MyList<Value>();
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        PrgState state= new PrgState(stk, symtbl, out, ex3);
        MyList<PrgState> states = new MyList<>();
        states.add(state);
        MemoryRepository repository = new MemoryRepository(states);
        this.controller = new Controller(repository);
    }

    public void Start() throws MyException {
        boolean ok = true;
        while (ok) {
            Menu();
            Scanner input = new Scanner(System.in);
            try {
                int choice = input.nextInt();
                switch (choice) {
                    case 1: {
                        InputProgram();
                        Scanner input2 = new Scanner(System.in);
                        try {
                            int choice2 = input2.nextInt();
                            switch (choice2) {
                                case 2: continue;
                                case 1: {
                                    DataBase();
                                    Scanner input3 = new Scanner(System.in);
                                    try {
                                        int  choice3 = input3.nextInt();
                                        switch (choice3) {
                                            case 1 : {
                                                loadEx1();
                                                break;
                                            }
                                            case 2 : {
                                                loadEx2();
                                                break;
                                            }
                                            case 3 : {
                                                loadEx3();
                                                break;
                                            }
                                            default: break;
                                        }
                                    }
                                    catch (Exception e) {
                                        System.out.println("Invalid input");
                                    }
                                    break;
                                }
                                case 3 : break;
                                default: System.out.println("Invalid input");
                            }
                        }
                        catch (Exception e) {
                            System.out.println("Invalid input");
                        }
                        break;
                    }
                    case 2 : {
                        controller.allStep();
                        break;
                    }
                    case 3 : {
                        ok = false;
                        break;
                    }
                    default: System.out.println("Invalid input");
                }
            }
            catch (Exception e) {
                System.out.println("Invalid input");
            }
        }
    }
}
