import { useInput } from "../hooks/UseInput";

const HookExam = () => {
  const [input, onChange] = useInput();

  return (
    <div>
      <input value={input} onChange={onChange} />
    </div>
  );
};

export default HookExam;
