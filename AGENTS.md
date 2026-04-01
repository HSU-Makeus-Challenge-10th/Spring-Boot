# AGENTS.md

## Review guidelines
- Write review comments in Korean using concise, clear, and easy-to-scan wording.
- This repository is a Spring study repository, not a production service.
- Prioritize feedback that improves Spring understanding over feedback aimed at production hardening.
- Focus on recurring learning issues such as layering, naming, dependency injection, object responsibilities, JPA basics, N+1 risks, circular references, entity/DTO separation, exception handling, and test intent.
- Do not prioritize advanced production topics such as security auditing, Redis caching, high-end query tuning, large-scale traffic handling, or infrastructure-level architecture unless they block correct Spring learning.
- Review comments should explain: 1) what is problematic, 2) why it matters in Spring/Java terms, 3) how to improve it, and 4) which concept to study next.
- Treat preference-based style comments as suggestions, not must-fix issues.
- Review from a study perspective, not a product delivery perspective.
- Use the closest workspace-level AGENTS.md to adjust review depth for junior vs senior members.
- Always leave an overall PR comment in markdown.
- The overall PR comment is separate from inline/file-specific review comments. Do not paste one finding as the entire overall comment.
- Do not use the auto-generated review wrapper such as `### 💡 Codex Review` as a substitute for the required overall PR comment format.
- After inline findings are posted, add one additional general PR conversation comment that contains only the required overall format.
- Format the separate overall PR comment with exactly these headings: `### 전체요약` and `### 추가 학습/복습`.
- Write `### 전체요약` as 2-4 sentences of prose that summarize the PR as a whole, including the most important must-fix issues and the overall learning direction.
- Under `### 추가 학습/복습`, always use bullets in the form `* 키워드 : 간단설명` and include a short reason each topic is worth reviewing.
- Even if there is only one major finding, still write the separate overall PR comment in the required format instead of repeating the inline comment verbatim.
- Preferred separate overall PR comment template:
  ```md
  ### 전체요약
  이번 PR은 ...

  ### 추가 학습/복습
  * 키워드 : 간단설명
  * 키워드 : 간단설명
  ```
