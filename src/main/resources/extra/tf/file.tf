resource "local_file" "pet" {
	filename = var.filename
	content = "My favourite pet is ${random_pet.my-pet.id}"

    # Explicit dependency (not required in this case as the resource directly reference other resource)
	depends_on = [
		random_pet.my-pet
	]
}

resource "random_pet" "my-pet" {
	prefix = var.prefix
	separator = var.separator
	length = var.length
}

output pet-name {
	value = random_pet.my-pet.id
	description = "Record the value of id generated"
}