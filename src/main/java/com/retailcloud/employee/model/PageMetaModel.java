package com.retailcloud.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageMetaModel {
		private int page;
		private int size;
		private Long totalElements;
		private int totalPages;
}
