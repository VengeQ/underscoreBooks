package essential.part7.tc

object PersonEqualByEmail {
   implicit def emailEqual:Equal[Person]  = (f: Person, s: Person) => f.email == s.email

}

object PersonEqualByNameAndEmail {
   implicit def emailAndNameEqual:Equal[Person] = (f: Person, s: Person) => f.name == s.name && f.email == s.email

}
