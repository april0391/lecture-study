import { useEffect, useState } from "react";

import { fetchExpenses } from "../util/http.index";
import { useExpenses } from "../store/expenses-context";
import { getDateMinusDays } from "../util/date";

import ExpensesOutput from "../components/ExpensesOutput/ExpensesOutput";
import LoadingOverlay from "../components/UI/LoadingOverlay";
import ErrorOverlay from "../components/UI/ErrorOverlay";

export default function RecentExpenses() {
  const [isFetching, setIsFetching] = useState(true);
  const [error, setError] = useState();

  const { expenses, setExpenses } = useExpenses();

  useEffect(() => {
    async function getExpenses() {
      try {
        const expenses = await fetchExpenses();
        setExpenses(expenses);
      } catch (error) {
        setError("Could not fetch expenses!");
      }

      setIsFetching(false);
    }

    getExpenses();
  }, []);

  function errorHandler() {
    setError(null);
  }

  if (error && !isFetching)
    return <ErrorOverlay message={error} onConfirm={errorHandler} />;

  if (isFetching) return <LoadingOverlay />;

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
