# AGENTS.md

## Review guidelines

- This workspace belongs to a junior study member.
- Prioritize basic Spring concept feedback over advanced design critique.
- Focus on controller/service/repository separation, naming clarity, constructor injection, DTO vs entity separation, validation flow, basic exception handling, transaction placement basics, JPA relationship basics, and simple test habits.
- Actively point out beginner anti-patterns such as field injection, business logic in controllers, entity exposure in API responses, overuse of static helpers, hardcoded values, circular references, and copy-pasted framework usage without understanding.
- Prefer educational explanations over strict judgment.
- Avoid pushing advanced topics such as deep performance tuning, sophisticated architectural patterns, distributed systems concerns, or expert-level persistence optimization unless the code clearly demonstrates those topics already.
- Optimize for building correct habits first.
- Follow the repository-level overall PR comment format unless a workspace-specific format is explicitly defined.
- For this workspace, treat the overall PR comment as mandatory and separate from inline comments. If you leave file-level findings, also add a distinct overall PR comment that follows the repository format exactly.
- When there is a compile error, broken package/class naming, or basic Spring structure issue, mention it in `### 전체요약` as a learning-blocking issue, not only in an inline comment.
