export default [
  {
    title: '一、财务状况',
    issueList: [
      {
        question: '1、您的主要收入来源是',
        score: null,
        answerList: [
          {
            'select': 'A．无固定收入（1分）',
            'score': '1'
          },
          {
            'select': 'B. 工资、劳务报酬（3分）',
            'score': '3'
          },
          {
            'select': 'C．生产经营所得（5分）',
            'score': '5'
          },
          {
            'select': 'D．利息、股息、转让证券等金融性资产收入（5分）',
            'score': '5'
          },
          {
            'select': 'E．出租、出售房地产等非金融性资产收入（5分）',
            'score': '5'
          }
        ]
      },
      {
        question: '2、最近您家庭预计进行投资的资金占家庭现有总资产(不含自住、自用房产及汽车等固定资产)的比例是：',
        score: null,
        answerList: [
          {
            'select': 'A．70%以上（1分）',
            'score': '1'
          },
          {
            'select': 'B．50%-70%（2分）',
            'score': '2'
          },
          {
            'select': 'C．30%－50%（3分）',
            'score': '3'
          },
          {
            'select': 'D．10%－30%（4分）',
            'score': '4'
          },
          {
            'select': 'E．10%以下（5分）',
            'score': '5'
          }
        ]
      },
      {
        question: '3、您是否有尚未清偿的数额较大的债务，如有，其性质是：',
        score: null,
        answerList: [
          {
            'select': 'A．有，住房抵押贷款等长期定额债务（1分）',
            'score': '1'
          },
          {
            'select': 'B．有，信用卡欠款、消费信贷等短期信用债务（3分）',
            'score': '2'
          },
          {
            'select': 'C．有，亲朋之间借款（3分）',
            'score': '3'
          },
          {
            'select': 'D．没有（5分）',
            'score': '5'
          }
        ]
      },
      {
        question: '4、您可用于投资的资产数额（包括金融资产和不动产）为：',
        score: null,
        answerList: [
          {
            'select': 'A．不超过50万元人民币（1分）',
            'score': '1'
          },
          {
            'select': 'B．50万-300万元（不含）人民币（4分）',
            'score': '4'
          },
          {
            'select': 'C．300万-1000万元（不含）人民币（4分）',
            'score': '4'
          },
          {
            'select': 'D．1000万元人民币以上（5分）',
            'score': '5'
          }
        ]
      }
    ]
  },
  {
    title: '二、投资知识',
    issueList: [
      {
        question: '5、以下描述中何种符合您的实际情况',
        score: null,
        answerList: [
          {
            'select': 'A．没有投资知识、经验，不符合以下任何一项描述（1分）',
            'score': '1'
          },
          {
            'select': 'B．已取得金融、经济或财会等与金融产品投资相关专业学士以上学位（3分）',
            'score': '3'
          },
          {
            'select': 'C．现在或此前曾从事金融、经济或财会等与金融产品投资相关的工作超过两年（4分）',
            'score': '4'
          },
          {
            'select': 'D．取得证券从业资格、期货从业资格、注册会计师证书（CPA）或注册金融分析师证书（CFA）中的一项及以上（5分）',
            'score': '5'
          }
        ]
      }
    ]
  },
  {
    title: '三、投资经验',
    issueList: [
      {
        question: '6、 您的投资经验可以被概括为：',
        score: null,
        answerList: [
          {
            'select': 'A．有限：除银行活期账户和定期存款外，我基本没有其他投资经验（1分）',
            'score': '1'
          },
          {
            'select': 'B．一般：除银行活期账户和定期存款外，我购买过基金、保险等理财产品，但还需要进一步的指导（3分）',
            'score': '4'
          },
          {
            'select': 'C．丰富：我是一位有经验的投资者，参与过股票、基金等产品的交易，并倾向于自己做出投资决策（4分）',
            'score': '4'
          },
          {
            'select': 'D．非常丰富：我是一位非常有经验的投资者，参与过权证、期货或创业板等高风险产品的交易（5分）',
            'score': '5'
          }
        ]
      },
      {
        question: '7、有一位投资者一个月内做了15笔交易（同一品种买卖各一次算一笔），您认为这样的交易频率：',
        score: null,
        answerList: [
          {
            'select': 'A．太高了（1分）',
            'score': '1'
          },
          {
            'select': 'B．偏高（3分）',
            'score': '3'
          },
          {
            'select': 'C．正常（4分）',
            'score': '4'
          },
          {
            'select': 'D．偏低（5分）',
            'score': '5'
          }
        ]
      },
      {
        question: '8、过去一年时间内，您购买的不同金融产品（含同一类型的不同金融产品）的数量是：',
        score: null,
        answerList: [
          {
            'select': 'A．5个以下（1分）',
            'score': '1'
          },
          {
            'select': 'B．6至10个（3分）',
            'score': '3'
          },
          {
            'select': 'C．11至15个（4分）',
            'score': '4'
          },
          {
            'select': 'D．16个以上（5分）',
            'score': '5'
          }
        ]
      },
      {
        question: '9、以下金融产品，您投资经验在两年以上的有：（注：本题可多选，但评分以其中最高分值选项为准。）',
        type: 'multiple',
        scoreList: [],
        answerList: [
          {
            'select': 'A．银行存款（1分）',
            'score': '1'
          },
          {
            'select': 'B．债券、货币市场基金、债券型基金或其它固定收益类产品（1分）',
            'score': '1'
          },
          {
            'select': 'C．股票、混合型基金、偏股型基金、股票型基金等权益类投资（5分）',
            'score': '5'
          },
          {
            'select': 'D．期货、融资融券（5分）',
            'score': '5'
          },
          {
            'select': 'E．复杂金融产品或其他产品（5分）',
            'score': '5'
          }
        ]
      },
      {
        question: '10、如果您曾经从事过金融产品投资，在交易较为活跃的月份，平均月交易额大概是多少：',
        score: null,
        answerList: [
          {
            'select': 'A．从未投资过金融产品（1分）',
            'score': '1'
          },
          {
            'select': 'B．10万元以内（2分）',
            'score': '2'
          },
          {
            'select': 'C．10万元-30万元（3分）',
            'score': '3'
          },
          {
            'select': 'D．30万元-100万元（4分）',
            'score': '4'
          },
          {
            'select': 'E．100万元以上（5分）',
            'score': '5'
          }
        ]
      }
    ]
  },
  {
    title: '四、投资目标',
    issueList: [
      {
        question: '11、您用于金融投资的大部分资金不会用作其它用途的时间段为：',
        score: null,
        answerList: [
          {
            'select': 'A．短期——0到1年（1分）',
            'score': '1'
          },
          {
            'select': 'B．中期——1到5年（4分）',
            'score': '4'
          },
          {
            'select': 'C．长期——5年以上（5分）',
            'score': '5'
          }
        ]
      },
      {
        question: '12、您打算重点投资于哪些种类的投资品种？（注：本题可多选，但评分以其中最高分值选项为准。',
        type: 'multiple',
        scoreList: [],
        answerList: [
          {
            'select': 'A．债券、货币市场基金、债券基金等固定收益类投资（1分）',
            'score': '1'
          },
          {
            'select': 'B．股票、混合型基金、偏股型基金、股票型基金等权益类投资（3分）',
            'score': '3'
          },
          {
            'select': 'C．期货、融资融券（5分）',
            'score': '5'
          },
          {
            'select': 'D．复杂或高风险金融产品（5分）',
            'score': '5'
          },
          {
            'select': 'E．其他产品（3分）',
            'score': '3'
          }
        ]
      },
      {
        question: '13、假设有两种不同的投资：投资A预期获得5%的收益，有可能承担非常小的损失；投资B预期获得20%的收益，但有可能面临25%甚至更高的亏损。您将您的投资资产分配为：',
        score: null,
        answerList: [
          {
            'select': 'A．全部投资于A（1分）',
            'score': '1'
          },
          {
            'select': 'B．大部分投资于A（2分）',
            'score': '2'
          },
          {
            'select': 'C．两种投资各一半（3分）',
            'score': '3'
          },
          {
            'select': 'D．大部分投资于B（4分）',
            'score': '4'
          },
          {
            'select': 'E．全部投资于B（5分）',
            'score': '5'
          }
        ]
      }
    ]
  }, {
    title: '五、风险偏好',
    issueList: [
      {
        question: '14、当您进行投资时，您的首要目标是：',
        score: null,
        answerList: [
          {
            'select': 'A．资产保值，我不愿意承担任何投资风险（1分）',
            'score': '1'
          },
          {
            'select': 'B．尽可能保证本金安全，不在乎收益率比较低（2分）',
            'score': '2'
          },
          {
            'select': 'C．产生较多的收益，可以承担一定的投资风险（4分）',
            'score': '4'
          },
          {
            'select': 'D．实现资产大幅增长，愿意承担很大的投资风险（5分）',
            'score': '5'
          }
        ]
      }, {
        question: '15、您认为自己能承受的最大投资损失是多少？',
        score: null,
        answerList: [
          {
            'select': 'A．10%以内（1分）',
            'score': '1'
          },
          {
            'select': 'B．10%-30%（3分）',
            'score': '3'
          },
          {
            'select': 'C．30%-50%（4分）',
            'score': '4'
          },
          {
            'select': 'D．超过50%（5分）',
            'score': '5'
          }
        ]
      }, {
        question: '16、您打算将自己的投资回报主要用于：',
        score: null,
        answerList: [
          {
            'select': 'A．偿付债务（1分）',
            'score': '1'
          },
          {
            'select': 'B．履行扶养、抚养或赡养义务（2分）',
            'score': '2'
          },
          {
            'select': 'C．改善生活（3分）',
            'score': '3'
          },
          {
            'select': 'D．本人养老或医疗（4分）',
            'score': '4'
          },
          {
            'select': 'E．个体生产经营或证券投资以外的投资行为（5分）',
            'score': '5'
          }
        ]
      }
    ]
  }, {
    title: '六、其他信息',
    issueList: [
      {
        question: '17、您的年龄是：',
        score: null,
        answerList: [
          {
            'select': 'A．18-30岁（2分）',
            'score': '2'
          },
          {
            'select': 'B．31-40岁（5分）',
            'score': '5'
          },
          {
            'select': 'C．41-50岁（4分）',
            'score': '4'
          },
          {
            'select': 'D．51-60岁（3分）',
            'score': '3'
          },
          {
            'select': 'E．超过60岁（1分）',
            'score': '1'
          }
        ]
      }, {
        question: '18、今后五年时间内，您的父母、配偶以及未成年子女等需负法定抚养、扶养和赡养义务的人数为：',
        score: null,
        answerList: [
          {
            'select': 'A．5人以上（1分）',
            'score': '1'
          },
          {
            'select': 'B．3-4人（2分）',
            'score': '2'
          },
          {
            'select': 'C．1-2人（4分）',
            'score': '4'
          },
          {
            'select': 'D.无（5分）',
            'score': '5'
          }
        ]
      }, {
        question: '19、您的最高学历是：',
        score: null,
        answerList: [
          {
            'select': 'A．高中或以下（1分）',
            'score': '1'
          },
          {
            'select': 'B．大学专科（3分）',
            'score': '3'
          },
          {
            'select': 'C．大学本科（4分）',
            'score': '4'
          },
          {
            'select': 'D．硕士及以上（5分）',
            'score': '5'
          }
        ]
      }, {
        question: '20、您家庭的就业状况是：',
        score: null,
        answerList: [
          {
            'select': 'A．未婚，目前暂无稳定收入的工作 （1分）',
            'score': '1'
          },
          {
            'select': 'B．未婚，但有稳定收入的工作（3分）',
            'score': '3'
          },
          {
            'select': 'C．您与配偶均没有稳定收入的工作或者已退休（0分）',
            'score': '0'
          },
          {
            'select': 'D．您与配偶其中一人有稳定收入的工作（2分）',
            'score': '2'
          },
          {
            'select': '您与配偶均有稳定收入的工作（5分）',
            'score': '5'
          }
        ]
      }
    ]
  }
]
