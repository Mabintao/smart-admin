package net.lab1024.smartadmin.util;

import lombok.extern.slf4j.Slf4j;
import net.lab1024.smartadmin.module.business.erp.domain.dto.SpecCombineDTO;
import net.lab1024.smartadmin.module.business.erp.domain.dto.SpecDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SmartSpecUtil {
    /**
     * 规格组合，将所有的规格按照规格名称进行分组
     * 分组后进行规格的组合
     *
     * @param list
     * @return
     */
    public static List<SpecCombineDTO> specCombinations(List<SpecDTO> list) {
        List<List<SpecDTO>> formats = formatSpecFromSpecList(list);
        List<List<SpecDTO>> descartes = new SmartAlgorithmUtil<SpecDTO>().descartes(formats);

        List<SpecCombineDTO> result = descartes.stream().map(p -> {
            List<String> ids = p.stream().map(q -> q.getId()).collect(Collectors.toList());
            List<String> formatAttr = p.stream().map(SpecDTO::format).collect(Collectors.toList());
            return new SpecCombineDTO(ids, formatAttr, p);
        }).collect(Collectors.toList());

        return result;
    }

    /**
     * 将同一规格进行分组
     *
     * @param list 商品规格列表
     * @return
     */
    private static List<List<SpecDTO>> formatSpecFromSpecList(List<SpecDTO> list) {
        // 按照规格种类进行分组
        List<List<SpecDTO>> groupByList = new ArrayList<>(list.stream()
                .collect(Collectors.groupingBy(SpecDTO::getAttr))
                .values());

        return groupByList;
    }

    public static void main(String[] args) {
        List<SpecDTO> list = new ArrayList<>();
        list.add(new SpecDTO("0", "风格", "简朴"));
        list.add(new SpecDTO("1", "风格", "风采"));
        list.add(new SpecDTO("2", "颜色", "黄"));
        list.add(new SpecDTO("3", "颜色", "白"));
        list.add(new SpecDTO("4", "颜色", "黑"));
        list.add(new SpecDTO("5", "尺寸", "41"));
        list.add(new SpecDTO("6", "尺寸", "42"));
        list.add(new SpecDTO("7", "尺寸", "43"));
        list.add(new SpecDTO("8", "尺寸", "44"));
        list.add(new SpecDTO("9", "级别", "高"));
        list.add(new SpecDTO("10", "级别", "中"));
        list.add(new SpecDTO("11", "级别", "低"));

        List<SpecCombineDTO> lists = specCombinations(list);
        lists.forEach(p -> System.out.println(p.getFormatAttrs()));
        System.out.println(lists.size());


    }
}
