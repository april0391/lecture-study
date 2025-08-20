import { useExpenses } from "../store/expenses-context";

import ExpensesOutput from "../components/ExpensesOutput/ExpensesOutput";

export default function AllExpenses() {
  const { expenses } = useExpenses();

  return (
    <ExpensesOutput
      expenses={expenses}
      expensePeriod="Total"
      fallbackText="No registered expenses found!"
    />
  );
}
