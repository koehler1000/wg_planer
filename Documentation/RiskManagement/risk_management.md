## Risk Management

The Risk Management Table lists potential risks and values them regarding their impact and probability of occurrence.
Every potential risk has a "Person in Charge" assigned who keeps track of how the risks impact on the project.
The first 20 percent of the risks which have the highest risk factor get a "Risk Mitigation" which is used to prevent the risk from becoming a real problem.

| Risk Name | Risk Description | Risk Probability of Occurrence | Risk Impact (0 - 10) | Risk Factor | Risk Mitigation | Person in Charge of Tracking | Affects |
| --------- | ---------------- | :---------------------------: | :---------: | :---------: | --------------- | :--------------------------: | ------- |
| Distraction | Private issues of team members cause delays (Sickness, etc.) | 25% | 8 | 2 | Adjust time plan | Arne | Schedule |
| Timing | The time planned is insufficient | 20% | 8 | 1.6 | Plan tasks with time buffer | Arne | Schedule |
| Framework API | Updates of Framework API cause problems | 20% | 8 | 1.6 | Sandbox updates first | Andre | Performance |
| Setup | Updates of IDEs or Tools cause problems | 20% | 5 | 1 | Sandbox updates first | Andre | Performance |
| Not usable | product is not usable for end user | 5% | 10 | 0.5 | Early testing; using [codecov][codecov] | Arne | Performance |
| Quality | Code Quality is insufficient | 10% | 5 | 0.5 | Use quality tools like [codacy][codacy] and [goreportcard][goreportcard] | Andre | Performance |
| Deployment | Server deployment unsuccessfull | 5% | 8 | 0.4 | Use continuous deployment | Andre | Performance |
| Gradle | Gradle updates cause build failure | 5% | 8 | 0.4 | Test updates locally | Arne | Performance |
| Build | Client Build fails | 5% | 6 | 0.3 | Use continuous integration services like [travis][travis] | Arne | Performance |
| Licensing | Licenses of used libraries, etc. cause problems | 2% | 10 | 0.2 | Read licenses thoroughly | Andre | Costs 

[codacy]: https://app.codacy.com
[goreportcard]: https://goreportcard.com
[travis-1]: https://travis-ci.org/WGPlaner/wg_planer_server
[codecov]: https://codecov.io/gh/WGPlaner/wg_planer_server
