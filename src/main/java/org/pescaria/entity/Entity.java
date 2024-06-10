package org.pescaria.entity;

public abstract class Entity {
	public int id;

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Entity entity = (Entity) obj;
		return id == entity.id;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}
}
