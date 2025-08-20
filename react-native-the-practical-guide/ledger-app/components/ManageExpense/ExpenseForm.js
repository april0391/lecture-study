import { useState } from "react";
import { StyleSheet, Text, View } from "react-native";

import Input from "./Input";
import Button from "../UI/Button";
import { getFormattedDate } from "../../util/date";

export default function ExpenseForm({
  onCancel,
  submitButtonLabel,
  onSubmit,
  editingExpense,
}) {
  const [inputValues, setInputValues] = useState({
    amount: editingExpense?.amount ? String(editingExpense?.amount) : "",
    date: editingExpense?.date ? getFormattedDate(editingExpense.date) : "",
    description: editingExpense?.description || "",
  });

  function inputChangedHandler(inputIdentifier, enteredValue) {
    setInputValues((prev) => ({ ...prev, [inputIdentifier]: enteredValue }));
  }

  function submitHandler() {
    const expenseData = {
      amount: Number(inputValues.amount),
      date: new Date(inputValues.date),
      description: inputValues.description,
    };

    onSubmit(expenseData);
  }

  return (
    <View style={styles.form}>
      <Text style={styles.title}>Your Expense</Text>
      <View style={styles.inputRow}>
        <Input
          style={styles.rowInput}
          label="Amount"
          textInputConfig={{
            keyboardType: "decimal-pad",
            onChangeText: (i) => inputChangedHandler("amount", i),
            value: inputValues.amount,
          }}
        />
        <Input
          style={styles.rowInput}
          label="Date"
          textInputConfig={{
            placeholder: "YYYY-MM-DD",
            maxLength: 10,
            onChangeText: (i) => inputChangedHandler("date", i),
            value: inputValues.date,
          }}
        />
      </View>
      <Input
        label="Description"
        textInputConfig={{
          multiline: true,
          // autoCapitalize: "sentence",
          onChangeText: (i) => inputChangedHandler("description", i),
          value: inputValues.description,
        }}
      />
      <View style={styles.buttons}>
        <Button style={styles.button} mode="flat" onPress={onCancel}>
          Cancel
        </Button>
        <Button style={styles.button} onPress={submitHandler}>
          {submitButtonLabel}
        </Button>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  form: {
    marginTop: 40,
  },
  title: {
    fontSize: 24,
    fontWeight: "bold",
    color: "white",
    marginVertical: 24,
    textAlign: "center",
  },
  inputRow: {
    flexDirection: "row",
    justifyContent: "space-between",
  },
  rowInput: {
    flex: 1,
  },
  buttons: {
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "center",
  },
  button: {
    minWidth: 120,
    marginHorizontal: 8,
  },
});
