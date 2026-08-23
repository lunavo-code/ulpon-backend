# 代理执行规则 / Agent Execution Rules

1. **中文生成执行计划 (Mandatory Execution Plan in Chinese)**：根据用户需求生成详细的执行计划，内容必须使用中文。
2. **禁止主动催促或提醒执行 (No Proactive Prompts for Execution)**：生成计划并呈现给用户后，必须停止并等待。在用户明确说明要执行计划之前，不需要提醒/催促用户（例如不要询问“是否可以开始执行”或使用 `ask_question` 确认），用户评估合理后会主动指示按计划执行。
3. **标准化与复用 (Standardization and Reuse)**：生成或修改代码时，尽可能使用当前项目已有依赖和复用已有方法，尽可能使项目标准化，避免引入不必要的第三方库或重复造轮子。
4. **聚焦需求与建设性建议 (Focus on Scope and Constructive Input)**：可以提供建设性的意见和建议，但不要做需求之外的事情，严禁生成冗余、无用的代码。
