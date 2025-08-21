import { createContext, useContext, useReducer } from "react";

export const ExpensesContext = createContext({
  expenses: [],
  addExpense: ({ description, amount, date }) => {},
  setExpenses: (expenses) => {},
  deleteExpense: (id) => {},
  updateExpense: (id, { description, amount, date }) => {},
});

function expensesReducer(state, action) {
  switch (action.type) {
    case "ADD":
      return [
        {
          ...action.payload,
        },
        ...state,
      ];
    case "SET":
      const inverted = action.payload.reverse();
      return inverted;
    case "UPDATE":
      return state.map((expense) =>
        expense.id === action.payload.id
          ? { id: action.payload.id, ...action.payload.data }
          : expense
      );
    case "DELETE":
      return [...state.filter((expense) => expense.id !== action.payload.id)];
    default:
      return state;
  }
}

export default function ExpensesContextProvider({ children }) {
  const [expensesState, dispatch] = useReducer(expensesReducer, []);

  function addExpense(expenseData) {
    dispatch({ type: "ADD", payload: expenseData });
  }

  function setExpenses(expenses) {
    dispatch({ type: "SET", payload: expenses });
  }

  function updateExpense(id, expenseData) {
    dispatch({ type: "UPDATE", payload: { id, data: expenseData } });
  }

  function deleteExpense(id) {
    dispatch({ type: "DELETE", payload: { id } });
  }

  return (
    <ExpensesContext
      value={{
        expenses: expensesState,
        addExpense,
        setExpenses,
        deleteExpense,
        updateExpense,
      }}
    >
      {children}
    </ExpensesContext>
  );
}

export function useExpenses() {
  const value = useContext(ExpensesContext);
  if (!value) throw new Error("Not provided expenses");

  return value;
}
