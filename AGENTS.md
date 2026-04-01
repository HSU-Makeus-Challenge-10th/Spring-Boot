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
- Format the overall PR comment with `### 전체요약` and `### 추가 학습/복습`; write `### 전체요약` as prose, and under `### 추가 학습/복습` use `* 키워드 : 간단설명` with a short reason for why each topic is recommended.