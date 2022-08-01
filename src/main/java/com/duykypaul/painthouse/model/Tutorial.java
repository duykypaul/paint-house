package com.duykypaul.painthouse.model;

import com.duykypaul.painthouse.dto.TutorialDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SqlResultSetMapping(
		name = "findAll1",
		classes = @ConstructorResult(
				targetClass = TutorialDTO.class,
				columns = {
						@ColumnResult(name = "id", type = Long.class),
						@ColumnResult(name = "title", type = String.class),
						@ColumnResult(name = "description", type = String.class),
						@ColumnResult(name = "published", type = Boolean.class)
				}
		)
)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tutorials")
public class Tutorial extends BaseEntity {

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "published")
	private boolean published;
}
