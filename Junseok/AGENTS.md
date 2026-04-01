# AGENTS.md

## Review guidelines

- This workspace belongs to a senior study member.
- In addition to basic Spring correctness, provide deeper feedback on design quality and reasoning.
- Focus on transaction boundaries, service cohesion, dependency direction, package/class responsibility, JPA relationship design, N+1 root causes, lazy-loading side effects, aggregate consistency, exception design, validation boundaries, test scope, and refactoring opportunities.
- Point out structural anti-patterns such as anemic service design, weak domain boundaries, over-coupled components, leaky abstractions, mixed read/write responsibilities, and unclear ownership of state changes.
- Advanced feedback is encouraged when it improves Spring and backend design understanding, but keep the review educational rather than production-ops focused.
- Deprioritize topics such as infrastructure security audits, Redis strategy, large-scale caching, extreme query tuning, and cloud-scale architecture unless directly relevant to the studied code.
- Assume the author can handle conceptually deeper feedback; explain trade-offs, not just fixes.