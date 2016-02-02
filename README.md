# data-skos

## Support

This table shows supported properties. Column *Property* is the name of the property as used in code.

| Property           | SKOS               | Collection | Concept | ConceptScheme |
| ------------------ | ------------------ | ---------- |-------- | ------------- |
| notation           | notation           | **Yes**    | **Yes** | **Yes**       |
| member             | member             | **Yes**    | No      | No            |
| **Label**                                                                      |
| preferred          | prefLabel          | **Yes**    | **Yes** | **Yes**       |
| alternative        | altLabel           | **Yes**    | **Yes** | **Yes**       |
| hidden             | hiddenLabel        | **Yes**    | **Yes** | **Yes**       |
| **Documentation**                                                              |
| note               | note               | **Yes**    | **Yes** | **Yes**       |
| changeNote         | changeNote         | **Yes**    | **Yes** | **Yes**       |
| definition         | definition         | **Yes**    | **Yes** | **Yes**       |
| editorialNote      | editorialNote      | **Yes**    | **Yes** | **Yes**       |
| example            | example            | **Yes**    | **Yes** | **Yes**       |
| historyNote        | historyNote        | **Yes**    | **Yes** | **Yes**       |
| scopeNote          | scopeNote          | **Yes**    | **Yes** | **Yes**       |
| **Relation**                                                                   |
| semanticRelation   | semanticRelation   | No         | **Yes** | No            |
| related            | related            | No         | **Yes** | No            |
| broader            | broader            | No         | **Yes** | No            |
| narrower           | narrower           | No         | **Yes** | No            |
| broaderTransitive  | broaderTransitive  | No         | **Yes** | No            |
| narrowerTransitive | narrowerTransitive | No         | **Yes** | No            |
| **Scheme**                                                                     |
| in                 | inScheme           | No         | **Yes** | No            |
| hasTop             | hasTopConcept      | No         | No      | **Yes**       |
| topOf              | topConceptOf       | No         | **Yes** | No            |
| **Mapping**                                                                    |
| mappingRelation    | mappingRelation    | No         | **Yes** | No            |
| closeMatch         | closeMatch         | No         | **Yes** | No            |
| exactMatch         | exactMatch         | No         | **Yes** | No            |
| broadMatch         | broadMatch         | No         | **Yes** | No            |
| narrowMatch        | narrowMatch        | No         | **Yes** | No            |
| relatedMatch       | relatedMatch       | No         | **Yes** | No            |



