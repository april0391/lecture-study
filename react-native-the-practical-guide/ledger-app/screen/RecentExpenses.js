import { useExpenses } from "../store/expenses-context";

import { getDateMinusDays } from "../util/date";

import ExpensesOutput from "../components/ExpensesOutput/ExpensesOutput";

export default function RecentExpenses() {
  const { expenses } = useExpenses();

  const today = new Date();
  const date7DaysAgo = getDateMinusDays(today, 7);

  const recentExpenses = expenses.filter(
    (expense) => expense.date > date7DaysAgo && expense.date <= today
  );

  return (
    <ExpensesOutput
      expenses={recentExpenses}
      expensePeriod="Last 7 Days"
      fallbackText="No expenses registered for the last 7 days."
    />
  );
}
