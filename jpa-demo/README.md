# JPA demo: every association once

This module maps every association type once, one class per type. It has no tests and no
business logic. The proof is the DDL that Hibernate prints at boot.

## The four questions

Ask these in this order for every relation.

- Who is the parent? The parent owns the relation in the domain.
- Who is the child? The child is the one that cannot live alone.
- Where is the foreign key? Always in the child table.
- Which annotation? The child gets the annotation without `mappedBy`.

## The table

| Relationship | Classes | Annotation on the child | Annotation on the parent | DDL shows |
| --- | --- | --- | --- | --- |
| 1:1 unidirectional | Ticket, Trip | `@OneToOne(optional = false)` | none | `ticket.trip_id not null unique` |
| 1:1 bidirectional | Passport, Person | `@OneToOne(optional = false)` | `@OneToOne(mappedBy = "person")` | `passport.person_id not null unique` |
| 1:n unidirectional | Photo, Trip | `@ManyToOne(optional = false)` | none | `photo.trip_id not null` |
| 1:n bidirectional composition | Trip, Person | `@ManyToOne(optional = false)` | `@OneToMany(mappedBy = "person", cascade = ALL, orphanRemoval = true)` | `trip.person_id not null` |
| n:m | Person, Language | `@ManyToMany(mappedBy = "languages")` | `@ManyToMany` plus `@JoinTable` | table `person_language` with a primary key over both columns |
| value object | Address in Person | none | plain field of an `@Embeddable` record | columns `street` and `city` in `person` |
| 1:n value objects | previousAddresses in Person | none | `@ElementCollection` | table `person_previous_addresses` with `person_id`, `street`, `city` |
| inheritance | Vehicle, Car, Bike | `@DiscriminatorValue` | `@Inheritance(SINGLE_TABLE)` plus `@DiscriminatorColumn` | one table `vehicle` with column `vehicle_type`, child columns nullable, Hibernate adds a check per type for primitive fields |
| rich type | not here, see the app module | `AttributeConverter` | none | one column of a database type |

## Three rules

- A 1:1 relation is always bidirectional, so the parent can reach its child. This is our course rule, the price is one extra query per parent load.
- A 1:n relation is unidirectional from the child, unless it is a composition. Only then
  does the parent get a list.
- An n:m relation with an attribute on the relation becomes its own entity. Example
  `Booking(person, trip, seat)`, not implemented here.

## Owning and inverse side

The field without `mappedBy` is the owning side. It holds the foreign key and it is the
one Hibernate saves. The field with `mappedBy` builds nothing, it only reads. So the
parent needs a business method that sets both sides, see `Person.addLanguage`. Never a
setter.

## Cascade and orphanRemoval

Both belong inside a composition only, where the parent really owns the children.
`cascade = CascadeType.ALL` saves and deletes the children with the parent,
`orphanRemoval = true` deletes a child that is removed from the list. Never put cascade on
`@ManyToOne`, that would delete the parent together with the child.

## How to look

```bash
./gradlew :jpa-demo:bootRun
```

The DDL stands in the log. The H2 console is at `http://localhost:8080/h2-console`, with
the url `jdbc:h2:mem:jpademo`, user `sa` and an empty password.
