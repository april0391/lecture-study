import { RequestHandler } from "express";
import { Todo } from "../models/todos";

const TODOS: Todo[] = [];

export const createTodo: RequestHandler = (req, res) => {
  const text = (req.body as { text: string }).text;
  const newTodo = new Todo(Math.random().toString(), text);
  TODOS.push(newTodo);

  res.status(201).json({ message: "Created the todo.", createdTodo: newTodo });
};

export const getTodos: RequestHandler = (req, res) => {
  res.status(200).json({ todos: TODOS });
};

export const updateTodo: RequestHandler<{ id: string }> = (req, res) => {
  const todoId = req.params.id;
  const updatedText = (req.body as { text: string }).text;
  const todo = TODOS.find((todo) => todo.id === todoId);

  if (todo) {
    todo.text = updatedText;
    res.status(200).json({ message: "Updated todo.", updatedTodo: todo });
  } else {
    throw new Error("Could not find todo!");
  }
};

export const deleteTodo: RequestHandler<{ id: string }> = (req, res) => {
  const todoId = req.params.id;
  const todoIndex = TODOS.findIndex((todo) => todo.id === todoId);

  if (todoIndex >= 0) {
    TODOS.splice(todoIndex, 1);
    res.status(200).json({ message: "Deleted todo." });
  } else {
    throw new Error("Could not find todo!");
  }
};
